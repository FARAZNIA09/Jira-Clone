package com.jira_clone.task_management_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"jwt.secret=VGhpc0lzQVRlc3RTZWNyZXRLZXlGb3JKV1QxMjM0NTY=",
		"jwt.expiration=86400000"
})
class TaskManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}
}
