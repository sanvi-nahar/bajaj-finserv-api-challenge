package com.bajaj.bfhl;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import com.bajaj.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    @Test
    void testProcessData_withMixedInput() {
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("sanvi_nahar_31082004", response.getUserId());
        assertEquals("sanvinahar230695@acropolis.in", response.getEmail());
        assertEquals("0827CI231117", response.getRollNumber());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
    }

    @Test
    void testProcessData_onlyNumbers() {
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList("2", "3", "5", "10"));

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("3", "5"), response.getOddNumbers());
        assertEquals(List.of("2", "10"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("20", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void testProcessData_onlyAlphabets() {
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList("a", "b", "c", "d"));

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(List.of("A", "B", "C", "D"), response.getAlphabets());
        assertEquals("0", response.getSum());
        assertEquals("DcBa", response.getConcatString());
    }

    @Test
    void testProcessData_emptyData() {
        BfhlRequest request = new BfhlRequest();
        request.setData(List.of());

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void testConcatString_alternatingCaps() {
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList("a", "b", "c", "d"));

        BfhlResponse response = service.processData(request);

        assertEquals("DcBa", response.getConcatString());
    }
}
