package com.thezz9.schedulerjpa.common.config;

import org.junit.jupiter.api.*;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DisplayName("PasswordEncoder 테스트")
class PasswordEncoderTest {

    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private static PasswordEncoder encoder;

    @BeforeAll
    static void init() {
        encoder = new PasswordEncoder();
    }

    @BeforeEach
    void logStart() {
        testStartTime = LocalDateTime.now();
        log.info("테스트 시작 시각: {}", testStartTime);
    }

    @AfterEach
    void logEnd() {
        testEndTime = LocalDateTime.now();
        long durationMs = java.time.Duration.between(testStartTime, testEndTime).toMillis();
        log.info("테스트 종료 시각: {}", testEndTime);
        log.info("테스트 소요 시간: {}ms", durationMs);
    }

    @Nested
    @DisplayName("PasswordEncoder.encode() Tests")
    class EncodeTests {

        @Test
        @DisplayName("같은 입력에도 encode()는 항상 다른 해시를 생성해야 한다")
        void shouldReturnDifferentHashesEvenForSameInput() {

            // given
            String pwd1 = "123";
            String pwd2 = "123";
            String pwd3 = "456";

            // when
            String hash1 = encoder.encode(pwd1);
            String hash2 = encoder.encode(pwd2);
            String hash3 = encoder.encode(pwd3);

            // then
            assertNotNull(hash1);
            assertNotNull(hash2);
            assertNotNull(hash3);

            assertNotEquals(hash1, hash2); // 같은 입력이어도 다른 해시값 나와야 함
            assertNotEquals(hash1, hash3); // 서로 다른 입력
            assertNotEquals(hash2, hash3); // 서로 다른 입력

            log.info("hash1 = {}", hash1);
            log.info("hash2 = {}", hash2);
            log.info("hash3 = {}", hash3);

        }

        @Test
        @DisplayName("encode() 결과는 입력 길이와 무관하게 고정된 길이를 가진다.")
        void shouldReturnFixedLengthHashes() {

            // given
            String shortPwd = "123";
            String longPwd = "veryLongVeryLongWithSpecialChars!@%#";

            // when
            String hash1 = encoder.encode(shortPwd);
            String hash2 = encoder.encode(longPwd);

            // then
            assertNotNull(hash1);
            assertNotNull(hash2);

            assertEquals(hash1.length(), hash2.length());

            log.info("hash1.length = {}", hash1.length());
            log.info("hash2.length = {}", hash2.length());
        }
    }

    @Nested
    @DisplayName("PasswordEncoder.matches() Tests")
    class MatchesTests {

        @Test
        @DisplayName("matches()는 원래 비밀번호와 해시가 일치할 때 true를 반환한다.")
        void shouldReturnTrueForMatchingPassword() {

            // given
            String rawPassword = "beforeEncode";
            String encodedPassword = encoder.encode(rawPassword);

            // when
            boolean result = encoder.matches(rawPassword, encodedPassword);

            // then
            assertTrue(result);
        }
    }
}