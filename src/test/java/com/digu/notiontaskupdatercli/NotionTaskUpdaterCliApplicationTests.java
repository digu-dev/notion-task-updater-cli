package com.digu.notiontaskupdatercli;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
@ActiveProfiles("test")
class NotionTaskUpdaterCliApplicationTests {

    @Test
    void contextLoads() {
    }
}
