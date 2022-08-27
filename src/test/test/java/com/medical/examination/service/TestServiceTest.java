package com.medical.examination.service;

import com.medical.examination.entity.Account;
import com.medical.examination.entity.TestResult;
import com.medical.examination.entity.TestType;
import com.medical.examination.repository.TestRepository;
import com.medical.examination.repository.TestResultRepository;
import com.medical.examination.service.impl.TestResultServiceImpl;
import com.medical.examination.service.impl.TestServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)

class TestServiceTest {
    @Mock
    private TestRepository testRepository;
    @InjectMocks
    private TestServiceImpl testService;

    private com.medical.examination.entity.Test test;

    @BeforeEach
    void setUp() {
        TestType testType = TestType.builder().id(1L).typeName("XN Hóa Sinh").status(1).build();
        test = com.medical.examination.entity.Test.builder().id(1L).testName("Glucozo").testType(testType)
                .costPrice(80000.0).sellPrice(120000.0).status(1L)
                .build();
    }

    @Test
    void saveTest() {
        // given - precondition or setup
        given(testRepository.save(test)).willReturn(test);
        // when -  action or the behaviour that we are going test
        com.medical.examination.entity.Test test1 = testService.saveTest(test);
        // then - verify the output
        Assertions.assertThat(test1).isEqualTo(test);
        Assertions.assertThat(test1).isNotNull();
    }

    @Test
    void deleteTest() {
    }

    @Test
    void getTestById() {
        // given - precondition or setup
        given(testRepository.getTestById(test.getId())).willReturn(test);

        // when -  action or the behaviour that we are going test
        com.medical.examination.entity.Test test1 = testService.getTestById(test.getId());
        // then - verify the output
        Assertions.assertThat(test1).isNotNull();
    }

    @Test
    void getTestByTestName() {
        // given - precondition or setup
        given(testRepository.getTestByTestName(test.getTestName())).willReturn(test);

        // when -  action or the behaviour that we are going test
        com.medical.examination.entity.Test test1 = testService.getTestByTestName(test.getTestName());
        // then - verify the output
        Assertions.assertThat(test1.getTestName()).isEqualTo(test.getTestName());
        Assertions.assertThat(test1).isNotNull();
    }

    @Test
    void findByTestName() {
        // given - precondition or setup
        given(testRepository.findByTestName("Glu")).willReturn(test);

        // when -  action or the behaviour that we are going test
        com.medical.examination.entity.Test test1 = testService.findByTestName("Glu");
        // then - verify the output
        Assertions.assertThat(test1.getTestName()).isEqualTo(test.getTestName());
        Assertions.assertThat(test1).isNotNull();
    }

    @Test
    void findTest() {
    }

    @Test
    void updateTestStatus() {
        // given - precondition or setup
        doNothing().when(testRepository).updateTestStatus(test.getId(), 0L);
        // when -  action or the behaviour that we are going test
        testService.updateTestStatus(test.getId(), 0L);
        // then - verify the output
        Mockito.verify(testRepository, times(1)).updateTestStatus(test.getId(),0L);
    }

    @Test
    void saveAllTest() {
        TestType testType = TestType.builder().id(1L).typeName("XN Hóa Sinh").status(1).build();
        com.medical.examination.entity.Test test1 = com.medical.examination.entity.Test.builder().id(2L).testName("Axit Amin").testType(testType)
                .costPrice(70000.0).sellPrice(130000.0).status(1L)
                .build();
        List<com.medical.examination.entity.Test> lstTest = List.of(test,test1);
        given(testRepository.saveAll(lstTest)).willReturn(List.of(test,test1));
        // when -  action or the behaviour that we are going test
        List<com.medical.examination.entity.Test> savedTest = testService.saveAllTest(lstTest);
        // then - verify the output
        Assertions.assertThat(savedTest).isNotNull();
        Assertions.assertThat(savedTest.size()).isEqualTo(2);
    }
}