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
import ro.myclass.billingmanagementapi.bill.rest.BillResource;
import ro.myclass.billingmanagementapi.bill.dto.BillDTO;
import ro.myclass.billingmanagementapi.bill.service.BillCommandService;
import ro.myclass.billingmanagementapi.bill.service.BillImplService;
import ro.myclass.billingmanagementapi.bill.service.BillQueryService;
import ro.myclass.billingmanagementapi.exceptions.ListEmptyException;
import ro.myclass.billingmanagementapi.bill.models.Bill;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class BillResourceTest {

    @Mock
    private BillCommandService billCommandService;

    @Mock
    private BillQueryService billQueryService;

    @InjectMocks
    private BillResource billResource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    public void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(billResource).build();
    }

    @Test
    public void getAllBills() throws Exception {

        Faker faker = new Faker();

        List<Bill> billList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //add bill with all properties
            billList.add(Bill.builder().id((long) i).number(faker.number().digits(10)).build());
        }

        doReturn(billList).when(billQueryService).getAllBills();

        restMockMvc.perform(get("/api/v1/bill/allBills"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(billList)));
    }

    @Test
    public void getAllBillsBadRequest() throws Exception {

        doThrow(ListEmptyException.class).when(billQueryService).getAllBills();

        restMockMvc.perform(get("/api/v1/bill/allBills"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addBill() throws Exception {
        Faker faker = new Faker();

        BillDTO bill = BillDTO.builder().number(faker.number().digits(10)).build();
        doNothing().when(billCommandService).addBill(bill);

        restMockMvc.perform(post("/api/v1/bill/addBill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bill)))
                .andExpect(status().isOk())
                .andExpect(content().string("Bill was added"));
    }

    @Test
    public void addBillBadRequest() throws Exception {
        Faker faker = new Faker();

        BillDTO bill = BillDTO.builder().number(faker.number().digits(10)).build();

        doThrow(ListEmptyException.class).when(billCommandService).addBill(bill);

        restMockMvc.perform(post("/api/v1/bill/addBill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bill)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBillById() throws Exception {
        Faker faker = new Faker();

        Bill bill = Bill.builder().id((long) 1).number(faker.number().digits(10)).build();

        doReturn(bill).when(billQueryService).getBillById(1);

        restMockMvc.perform(get("/api/v1/bill/getBillById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bill)));
    }

    @Test
    public void getBillByIdBadRequest() throws Exception {
        Faker faker = new Faker();

        Bill bill = Bill.builder().id((long) 1).number(faker.number().digits(10)).build();

        doThrow(ListEmptyException.class).when(billQueryService).getBillById(1);

        restMockMvc.perform(get("/api/v1/bill/getBillById/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getBillByNumber() throws Exception {
        Faker faker = new Faker();

        Bill bill = Bill.builder().id((long) 1).number(faker.number().digits(10)).build();

        doReturn(bill).when(billQueryService).getBillByNumber("number");

        restMockMvc.perform(get("/api/v1/bill/getBillByNumber/number"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bill)));
    }

    @Test
    public void getBillByNumberBadRequest() throws Exception {
        Faker faker = new Faker();

        Bill bill = Bill.builder().id((long) 1).number(faker.number().digits(10)).build();

        doThrow(ListEmptyException.class).when(billQueryService).getBillByNumber("number");

        restMockMvc.perform(get("/api/v1/bill/getBillByNumber/number"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deleteBill() throws Exception {
        Faker faker = new Faker();

        String number = faker.number().digits(10);

        doNothing().when(billCommandService).deleteBill(number);

        restMockMvc.perform(delete("/api/v1/bill/deleteBill/{number}", number))
                .andExpect(status().isOk())
                .andExpect(content().string("Bill was deleted"));
    }

    @Test
    public void deleteBillBadRequest() throws Exception {
        Faker faker = new Faker();

        String number = faker.number().digits(10);

        doThrow(ListEmptyException.class).when(billCommandService).deleteBill(number);

        restMockMvc.perform(delete("/api/v1/bill/deleteBill/{number}", number))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBill() throws Exception {
        Faker faker = new Faker();

        BillDTO bill = BillDTO.builder().number(faker.number().digits(10)).build();

        doNothing().when(billCommandService).updateBill(bill);

        restMockMvc.perform(put("/api/v1/bill/updateBill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bill)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBillBadRequest() throws Exception {
        Faker faker = new Faker();

        BillDTO bill = BillDTO.builder().number(faker.number().digits(10)).build();

        doThrow(ListEmptyException.class).when(billCommandService).updateBill(bill);

        restMockMvc.perform(put("/api/v1/bill/updateBill")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bill)))
                .andExpect(status().isBadRequest());
    }

  


}