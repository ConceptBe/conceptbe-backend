package kr.co.conceptbe.region.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import kr.co.conceptbe.member.exception.NotFoundRegionException;
import kr.co.conceptbe.region.domain.RegionName;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

class RegionNameTest {

    @Test
    void 올바른_지역으로_Region_을_생성하는_경우_성공한다() {
        //given
        String validName = "NO_MATTER";

        //when
        RegionName regionName = RegionName.from(validName);

        //then
        assertThat(regionName.getName()).isEqualTo("상관없음");
    }

    @Test
    void 올바르지_않은_지역으로_Region_을_생성하는_경우_실패한다() {
        //given
        String validName = "나는 아무거나 입력해";

        //when
        ThrowingCallable throwingCallable = () -> RegionName.from(validName);

        //then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(NotFoundRegionException.class);
    }


}
