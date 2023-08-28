package ro.myclass.billingmanagementapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.myclass.billingmanagementapi.receipt.dto.ReceiptDTO;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.receipt.models.Receipt;
import ro.myclass.billingmanagementapi.receipt.rest.ReceiptResource;
import ro.myclass.billingmanagementapi.receipt.service.ReceiptCommandService;
import ro.myclass.billingmanagementapi.receipt.service.ReceiptQuerryService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReceiptResourceTest {

    @Mock
    private ReceiptCommandService receiptCommandService;

    @Mock
    private ReceiptQuerryService receiptQuerryService;

    @InjectMocks
    private ReceiptResource receiptResource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(receiptResource).build();
    }

    @Test
    public void getAllReceipts() throws Exception {

        Faker faker = new Faker();

        List<Receipt> receiptList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add receipt with all properties from Receipt class
            receiptList.add(Receipt.builder().id((long) i).description(faker.lorem().sentence()).type(faker.lorem().word()).build());
        }

        doReturn(receiptList).when(receiptQuerryService).getAllReceipts();

        restMockMvc.perform(get("/api/v1/receipt/allReceipts"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(receiptList)));
    }

    @Test
    public void getAllReceiptBadRequests() throws Exception {
        doThrow(ListEmptyException.class).when(receiptQuerryService).getAllReceipts();

        restMockMvc.perform(get("/api/v1/receipt/allReceipts"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addReceipt() throws Exception {
        Faker faker = new Faker();

        ReceiptDTO receipt = ReceiptDTO.builder().description(faker.lorem().sentence()).type(faker.lorem().word()).number(faker.numerify("####")).build();

        doNothing().when(receiptCommandService).addReceipt(receipt);

        restMockMvc.perform(post("/api/v1/receipt/addReceipt").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(receipt)))
                .andExpect(status().isOk());
    }

    @Test
    public void addReceiptBadRequest() throws Exception {
        Faker faker = new Faker();

        ReceiptDTO receipt = ReceiptDTO.builder().description(faker.lorem().sentence()).type(faker.lorem().word()).number(faker.numerify("####")).build();

        doThrow(ListEmptyException.class).when(receiptCommandService).addReceipt(receipt);

        restMockMvc.perform(post("/api/v1/receipt/addReceipt").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(receipt)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteReceipt() throws Exception {
        Faker faker = new Faker();

        ReceiptDTO receipt = ReceiptDTO.builder().description(faker.lorem().sentence()).type(faker.lorem().word()).number(faker.numerify("####")).build();

        doNothing().when(receiptCommandService).addReceipt(receipt);

        restMockMvc.perform(post("/api/v1/receipt/addReceipt").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(receipt)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteReceiptBadRequest() throws Exception {
        Faker faker = new Faker();

        ReceiptDTO receipt = ReceiptDTO.builder().description(faker.lorem().sentence()).type(faker.lorem().word()).number(faker.numerify("####")).build();

        doThrow(ListEmptyException.class).when(receiptCommandService).addReceipt(receipt);

        restMockMvc.perform(post("/api/v1/receipt/addReceipt").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(receipt)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getReceiptById() throws Exception {
        Faker faker = new Faker();

        Receipt receipt = Receipt.builder().id((long) 1).description(faker.lorem().sentence()).type(faker.lorem().word()).number(faker.numerify("####")).build();

        doReturn(receipt).when(receiptQuerryService).getReceiptById((long) 1);

        restMockMvc.perform(get("/api/v1/receipt/getReceiptById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(receipt)));
    }

    @Test
    public void getReceiptByIdBadRequest() throws Exception {
        Faker faker = new Faker();

        Receipt receipt = Receipt.builder().id((long) 1).description(faker.lorem().sentence()).type(faker.lorem().word()).number(faker.numerify("####")).build();

        doThrow(ListEmptyException.class).when(receiptQuerryService).getReceiptById((long) 1);

        restMockMvc.perform(get("/api/v1/receipt/getReceiptById/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getReceiptByType() throws Exception {
        Faker faker = new Faker();

        List<Receipt> receiptList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add receipt with all properties from Receipt class
            receiptList.add(Receipt.builder().id((long) i).description(faker.lorem().sentence()).type(faker.lorem().word()).build());
        }

        doReturn(receiptList).when(receiptQuerryService).getReceiptByType("type");

        restMockMvc.perform(get("/api/v1/receipt/getReceiptByType/type"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(receiptList)));
    }

    @Test
    public void getReceiptByTypeBadRequest() throws Exception {
        Faker faker = new Faker();

        List<Receipt> receiptList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add receipt with all properties from Receipt class
            receiptList.add(Receipt.builder().id((long) i).description(faker.lorem().sentence()).type(faker.lorem().word()).build());
        }

        doThrow(ListEmptyException.class).when(receiptQuerryService).getReceiptByType("type");

        restMockMvc.perform(get("/api/v1/receipt/getReceiptByType/type"))
                .andExpect(status().isBadRequest());
    }




}