package kr.co.conceptbe.idea.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class IntroduceTest {

    @ParameterizedTest
    @ValueSource(strings = {"12345678901234567890", " 12345678901234567890 "})
    void 유효한_입력이_들어온_경우_생성에_성공한다(String input) {
        //given when
        Introduce introduce = Introduce.from(input);

        //then
        assertThat(introduce.getIntroduce()).isEqualTo(input.trim());
    }

    @ParameterizedTest
    @ValueSource(strings = {"                            ", "hellhjsdf         "})
    void 유효하지_않은_입력이_들어온_경우_생성에_실패한다(String input) {
        //given when
        ThrowingCallable throwingCallable = () -> Introduce.from(input);

        //then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(IllegalArgumentException.class);
    }

}
