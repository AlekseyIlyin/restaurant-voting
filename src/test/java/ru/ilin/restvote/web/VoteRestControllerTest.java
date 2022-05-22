package ru.ilin.restvote.web;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ilin.restvote.web.controller.VotingController;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static ru.ilin.restvote.RestaurantTestData.RESTAURANT1_ID;
import static ru.ilin.restvote.TestUtil.userHttpBasic;
import static ru.ilin.restvote.UserTestData.user;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VotingController.REST_URL + '/';

    @Test
    void vote() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(user)))
                .andDo(print());
    }
}