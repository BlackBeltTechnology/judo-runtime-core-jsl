# Development Version and Branch Handling

This document describes the branching strategy, version numbering, and CI/CD automation for the JUDO Runtime Core JSL project.

## Branches

The versioning policy follows a [GitFlow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow) model. Each branch type serves a specific purpose in the development lifecycle:

| Branch Pattern | Base | Purpose |
|---------------|------|---------|
| `develop` | — | Main development branch; contains latest development sources |
| `feature/JNG-xxx_summary` | `develop` | New features for the next release |
| `(release/)X.Y.Z` | `develop` | Release stabilization (`release/` prefix reserved for CI) |
| `bugfix/JNG-xxx_summary` | release branch | Bug fixes applied to a release under testing |
| `support/JNG-xxx_summary` | release branch | Minor changes to a previous release |
| `master` | — | Latest released sources |
| `hotfix/JNG-xxx_summary` | `master` | Critical fixes applied to both release and master |

### Branch Flow

```mermaid
gitGraph
    commit id: "init"
    branch develop
    checkout develop
    commit id: "dev-1"
    branch feature/JNG-1
    commit id: "feat-1"
    commit id: "feat-2"
    checkout develop
    merge feature/JNG-1 id: "merge-feat-1"
    branch feature/JNG-2
    commit id: "feat-3"
    checkout develop
    merge feature/JNG-2 id: "merge-feat-2"
    branch release/1.0-beta1
    commit id: "rc-1"
    branch bugfix/JNG-4
    commit id: "fix-1"
    checkout release/1.0-beta1
    merge bugfix/JNG-4 id: "merge-fix"
    checkout develop
    merge release/1.0-beta1 id: "merge-release"
    checkout master
    merge release/1.0-beta1 id: "release-1.0"
```

## Version Numbers

Version numbers follow semantic versioning with these rules:

| Event | Version Change | Example |
|-------|---------------|---------|
| Start `feature/` branch | No change | stays at `1.0.0-SNAPSHOT` |
| Start release branch from `develop` | 2nd number incremented on `develop` | `develop` becomes `1.1.0-SNAPSHOT` |
| Start `bugfix/` branch | No change | applied on release branch before merging to master |
| Start `support/` branch | 3rd number incremented | `1.0.1-SNAPSHOT` |
| Start `hotfix/` branch | 4th number incremented | `1.0.0.1-SNAPSHOT` |

### CI Version Calculation

On non-release branches, versions include build metadata for traceability:

- **Release branches → master**: `major.minor.qualifier` (e.g., `1.0.0`)
- **develop / feature branches**: `major.minor.qualifier.YYYYMMDD_HHMMSS_commitId_branchName` (e.g., `1.0.3.20260202_151641_1f52a549_develop`)

## GitHub Actions Workflows

The CI/CD system uses several interconnected GitHub Actions workflows that trigger each other via tags:

```mermaid
flowchart TD
    subgraph "build.yml"
        B_TRIGGER["Push to develop<br/>PR to develop/master/increment/release"]
        B_VERSION{{"Branch type?"}}
        B_RELEASE_VER["Version from pom.xml<br/>(without -SNAPSHOT)"]
        B_DEV_VER["Version: major.minor.qual<br/>.date_commitId_branch"]
        B_BUILD["Build & deploy to Nexus"]
        B_TAG["Create tag v&lt;version&gt;"]
        B_MERGE_TAG["Create tag<br/>merge-pr/&lt;version&gt;"]
        B_CHANGELOG["Build changelog &<br/>create GitHub prerelease"]

        B_TRIGGER --> B_VERSION
        B_VERSION -->|"master, release/*"| B_RELEASE_VER
        B_VERSION -->|"develop, increment/*"| B_DEV_VER
        B_RELEASE_VER --> B_BUILD
        B_DEV_VER --> B_BUILD
        B_BUILD --> B_TAG
        B_TAG -->|"increment/*, release/*"| B_MERGE_TAG
        B_TAG -->|"develop"| B_CHANGELOG
    end

    subgraph "merge-pr-tagged.yml"
        M_TRIGGER["Push on merge-pr/* tag"]
        M_CHECK{{"Version format?"}}
        M_MASTER["Merge PR to master"]
        M_DEVELOP["Squash PR to develop"]
        M_DELETE["Delete merge-pr/ tag"]

        M_TRIGGER --> M_CHECK
        M_CHECK -->|"major.minor.qualifier"| M_MASTER
        M_CHECK -->|"other"| M_DEVELOP
        M_MASTER --> M_DELETE
        M_DEVELOP --> M_DELETE
    end

    subgraph "create-release-on-master.yml"
        R_TRIGGER["Push on master"]
        R_RELEASE["Build changelog &<br/>create GitHub release"]

        R_TRIGGER --> R_RELEASE
    end

    subgraph "release.yml"
        REL_TRIGGER["Manual trigger<br/>(version: 'auto' or X.Y.Z)"]
        REL_PR_MASTER["Create PR to master<br/>with release version"]
        REL_PR_DEVELOP["Create PR to develop<br/>with next version"]

        REL_TRIGGER --> REL_PR_MASTER
        REL_TRIGGER --> REL_PR_DEVELOP
    end

    B_MERGE_TAG -.->|triggers| M_TRIGGER
    M_MASTER -.->|triggers| R_TRIGGER
    M_DEVELOP -.->|triggers| B_TRIGGER
    REL_PR_MASTER -.->|triggers| B_TRIGGER
    REL_PR_DEVELOP -.->|triggers| B_TRIGGER
```

### Build Pipeline Details

The `build.yml` workflow executes these steps:

```mermaid
flowchart LR
    CHECKOUT["Checkout<br/>+ JDK 21"] --> HSQLDB["Build & test<br/>(HSQLDB)"]
    HSQLDB --> PG["Test<br/>(PostgreSQL)"]
    PG --> NEXUS["Deploy to<br/>Judong Nexus"]
    NEXUS -->|"release/* only"| CENTRAL["Deploy to<br/>Maven Central"]
    CENTRAL --> SONAR["Sonar<br/>analysis"]
    SONAR --> TAG["Create<br/>version tag"]
    TAG --> RELEASE["GitHub<br/>release"]
```

Key build commands used by CI:

```bash
# Build & test with HSQLDB
./mvnw -B -T 4 -Drevision=<version> -Ddialect=hsqldb clean install

# Test with PostgreSQL
./mvnw -B -T 4 -Drevision=<version> -Ddialect=postgresql surefire:test

# Deploy (skip tests)
./mvnw -B -T 4 -Drevision=<version> -DdeployOnly -Dmaven.test.skip=true \
  -P"sign-artifacts,release-judong" deploy
```

## Development Rules

> **Important:** There is no commit without a ticket number. Every pull request or commit must include a JIRA reference (`JNG-xxx`).

Issue tracking: [JIRA Dashboard](https://blackbelt.atlassian.net/jira/dashboards)
