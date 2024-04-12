package kr.co.conceptbe.member.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NicknameTest {

    @ParameterizedTest
    @ValueSource(strings = {"간간", "가나123", "가나가나", "가나가나가나", "1234567890",
        "1234567890 "})
    void 유효한_입력을_하는_경우_정상적으로_생성한다(String input) {
        //given when
        Nickname nickname = Nickname.from(input);

        //then
        assertThat(nickname.getNickname()).isEqualTo(input.trim());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "!!!", "!!! !!!", "$123", "!@#$%^&*(", ")_+|`-=[]",
        "}{;':,./<>", "12345678901", "1"})
    void 유효하지_않은_입력을_하는_경우_생성에_실패한다(String input) {
        //given when
        ThrowingCallable throwingCallable = () -> Nickname.from(input);

        //then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(IllegalArgumentException.class);
    }

}
