package hu.blackbelt.judo.runtime.core.jsl.entity;

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relationtarget.RelationTarget;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relationtarget.RelationTargetAttribute;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relationtarget.RelationTargetForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.tester.Tester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.tester.TesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.tester.TesterForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.SpecialCasesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@EnabledIfSystemProperty(named = "dialect", matches = "postgresql")
public class HungarianStringQueryCustomizerTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("SpecialCases", new SpecialCasesDaoModules());

    @Inject
    TesterDao testerDao;

    public static final String PREFIX = "List_";

    @Test
    @TestCase("HungarianStringQueryCustomizer")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005"
    })
    public void testHungarianStringQueryCustomizer() {
        List<String> names = List.of("a", "A", "á", "Á", "b", "B", "c", "C", "cs", "cS", "Cs", "CS", "d", "D", "dz", "dZ", "Dz", "DZ", "dzs",
                "dzS", "dZs", "Dzs", "DzS", "DZs", "DZS", "e", "E", "é", "É", "f", "F", "g", "G", "gy", "gY", "Gy", "GY",
                "h", "H", "i", "I", "í", "Í", "j", "J", "k", "K", "l", "L", "ly", "lY", "Ly", "LY", "m", "M", "n", "N",
                "ny", "nY", "Ny", "NY", "o", "O", "ó", "Ó", "ö", "Ö", "ő", "Ő", "p", "P", "q", "Q", "r", "R", "s", "S",
                "sz", "sZ", "Sz", "SZ", "t", "T", "ty", "tY", "Ty", "TY", "u", "U", "ú", "Ú", "ü", "Ü", "ű", "Ű", "v",
                "V", "w", "W", "x", "X", "y", "Y", "Z", "zs", "zS", "Zs", "ZS");
        names = names.stream().map(n -> PREFIX + n).toList();

        Tester tester = testerDao.create(TesterForCreate.builder().build());

        for (String name : names) {
            testerDao.createRelationTargets(tester, RelationTargetForCreate.builder().withName(name).build());
        }

        // ASC
        List<String> relationTargetNames = testerDao.queryRelationTargets(tester)
                .orderBy(RelationTargetAttribute.NAME)
                .selectList()
                .stream().map(RelationTarget::getName).map(Optional::orElseThrow).toList();
        assertThat(relationTargetNames, equalTo(names));

        // DESC
        List<String> relationTargetNamesDescending = testerDao.queryRelationTargets(tester)
                .orderByDescending(RelationTargetAttribute.NAME)
                .selectList()
                .stream().map(RelationTarget::getName).map(Optional::orElseThrow).toList();
        List<String> namesReversed = new ArrayList<>(names);
        Collections.reverse(namesReversed);
        assertThat(relationTargetNamesDescending, equalTo(namesReversed));

        // FILTER
        List<String> relationTargetNamesFiltered = testerDao.queryRelationTargets(tester)
                .orderBy(RelationTargetAttribute.NAME)
                .filterBy(String.format("'%sa' <= this.name and this.name <= '%sÁ'", PREFIX, PREFIX))
                .selectList().stream().map(RelationTarget::getName).map(Optional::orElseThrow).toList();
        assertThat(relationTargetNamesFiltered, equalTo(names.subList(0, 4)));

        // ILIKE
        List<String> relationTargetNamesIlike = testerDao.queryRelationTargets(tester)
                .orderBy(RelationTargetAttribute.NAME)
                .filterBy("this.name!ilike('%ű')")
                .selectList().stream().map(RelationTarget::getName).map(Optional::orElseThrow).toList();
        int indexOfuuu = names.indexOf(PREFIX + "ű");
        assertThat(relationTargetNamesIlike, equalTo(names.subList(indexOfuuu, indexOfuuu + 2)));

        // PAGE
        List<String> relationTargetNamesPaged = testerDao.queryRelationTargets(tester)
                .orderBy(RelationTargetAttribute.NAME)
                .selectList(10,10).stream().map(RelationTarget::getName).map(Optional::orElseThrow).toList();
        assertThat(relationTargetNamesPaged, equalTo(names.subList(10, 20)));

    }
}
