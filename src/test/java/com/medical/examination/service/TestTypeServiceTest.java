package com.medical.examination.service;

import com.medical.examination.entity.TestType;
import com.medical.examination.repository.TestRepository;
import com.medical.examination.repository.TestTypeRepository;
import com.medical.examination.service.impl.TestServiceImpl;
import com.medical.examination.service.impl.TestTypeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)

class TestTypeServiceTest {
    @Mock
    private TestTypeRepository testTypeRepository;
    @Mock
    private TestRepository testRepository;
    @InjectMocks
    private TestTypeServiceImpl testTypeService;

    private TestType testType;
    private List<com.medical.examination.entity.Test> lstTest;
    @BeforeEach
    void setUp() {
        com.medical.examination.entity.Test test = com.medical.examination.entity.Test.builder().id(1L).testName("Glucozo").testType(testType)
                .costPrice(80000.0).sellPrice(120000.0).status(1L)
                .build();
        com.medical.examination.entity.Test test1 = com.medical.examination.entity.Test.builder().id(2L).testName("Axit").testType(testType)
                .costPrice(80000.0).sellPrice(120000.0).status(1L)
                .build();
        lstTest = List.of(test,test1);
        testType = TestType.builder().id(1L).typeName("XN Hóa Sinh").status(1).lstTest(lstTest).build();

    }

    @Test
    void saveTestType() {
        // given - precondition or setup
        given(testTypeRepository.save(testType)).willReturn(testType);
        // when -  action or the behaviour that we are going test
        TestType testType1 = testTypeService.saveTestType(testType);
        // then - verify the output
        Assertions.assertThat(testType1).isEqualTo(testType);
        Assertions.assertThat(testType1).isNotNull();
    }

    @Test
    void deleteTestType() {
    }

    @Test
    void getTestTypeById() {
        // given - precondition or setup
        given(testTypeRepository.getTestTypeById(testType.getId())).willReturn(testType);

        // when -  action or the behaviour that we are going test
        TestType testType1 = testTypeService.getTestTypeById(testType.getId());
        // then - verify the output
        Assertions.assertThat(testType1).isNotNull();
    }

    @Test
    void findTestType() {
    }

    @Test
    void changeStatusTestType() {
        com.medical.examination.entity.Test test = com.medical.examination.entity.Test.builder().id(1L).testName("Glucozo").testType(testType)
                .costPrice(80000.0).sellPrice(120000.0).status(1L)
                .build();
        // given - precondition or setup
        given(testTypeRepository.getTestTypeById(testType.getId())).willReturn(testType);
        given(testRepository.saveAll(testType.getLstTest())).willReturn(lstTest);
        
        // when -  action or the behaviour that we are going test
        Boolean changeStatus = testTypeService.changeStatusTestType(testType.getId(),"deactive");
        // then - verify the output
        Assertions.assertThat(changeStatus).isTrue();
        Assertions.assertThat(testType.getStatus()).isEqualTo(0L);
        for (com.medical.examination.entity.Test test_change : testType.getLstTest())
        {
            Assertions.assertThat(test_change.getStatus()).isEqualTo(0L);
        }
    }
}