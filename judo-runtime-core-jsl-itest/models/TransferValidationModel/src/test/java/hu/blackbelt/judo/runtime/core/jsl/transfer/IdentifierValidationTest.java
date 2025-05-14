package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;

import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.accountentity.AccountEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.accountentity.AccountEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.accountentity.AccountEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.identifierentity.IdentifierEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.identifierentity.IdentifierEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.identifierentity.IdentifierEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferValidationDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class IdentifierValidationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TransferValidation", new TransferValidationDaoModules(), null);

    @Inject
    AccountEntityDao accountEntityDao;

    @Inject
    IdentifierEntityDao identifierEntityDao;

    @Test
    public void testIdentifiersInTwoWayRelationOnEntity() {
        // account with inline identifier
        AccountEntity testAcc = accountEntityDao.create(AccountEntityForCreate
                .builder()
                .withName("testAcc")
                .withEmail("test.acc@mail.com")
                .withIdRel(IdentifierEntityForCreate.builder().withSalt("2a741295bb").build().adaptTo(IdentifierEntity.class))
                .build()
        );

        assertEquals("testAcc", testAcc.getName());
        assertEquals("test.acc@mail.com", testAcc.getEmail());
        assertEquals(1, accountEntityDao.countAll());
        assertEquals(1, identifierEntityDao.countAll());
        assertEquals("2a741295bb",  accountEntityDao.queryIdRel(testAcc).getSalt());

        IdentifierEntity identifier = identifierEntityDao.create(IdentifierEntityForCreate.builder().withSalt("2a741295bb1").build());

        // Set the idRel to an another identifier

        accountEntityDao.setIdRel(testAcc, identifier);
        testAcc = accountEntityDao.update(testAcc);

        assertEquals("2a741295bb1", accountEntityDao.queryIdRel(testAcc).getSalt());

        // Create an account with attached identifier

        AccountEntity testAcc2 = accountEntityDao.create(AccountEntityForCreate
                .builder()
                .withName("testAcc2")
                .withEmail("test.acc2@mail.com")
                .withIdRel(identifier)
                .build()
        );

        assertEquals("testAcc2", testAcc2.getName());
        assertEquals("test.acc2@mail.com", testAcc2.getEmail());
        assertEquals(2, accountEntityDao.countAll());
        assertEquals(2, identifierEntityDao.countAll());
        assertEquals("2a741295bb1",  accountEntityDao.queryIdRel(testAcc2).getSalt());

        // Create Identifier with an inline account
        IdentifierEntity identifier2 = identifierEntityDao.create(IdentifierEntityForCreate
                .builder()
                .withSalt("2a741295bb2")
                .build()
        );

        AccountEntity testAcc3 = identifierEntityDao.createAccounts(identifier2,
                AccountEntityForCreate
                        .builder()
                        .withName("testAcc3")
                        .withEmail("test.acc3@mail.com")
                        .withIdRel(identifier2)
                        .build()
        );

        assertEquals("testAcc3", testAcc3.getName());
        assertEquals("test.acc3@mail.com", testAcc3.getEmail());
        assertEquals(3, accountEntityDao.countAll());
        assertEquals(3, identifierEntityDao.countAll());
        assertEquals("2a741295bb2",  accountEntityDao.queryIdRel(testAcc3).getSalt());

    }

}
