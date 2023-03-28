package com.lyk.boardservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration // configuration bean으로 지정
public class JpaConfig {

    // 현재 생성자, 수정자에 대한 정보가 없기 때문에 임의의 데이터를 작성하여 동작하도록 한다.
    // 추후 인증 기능이 추가되면, 접속한 계정 정보에 따라 생성자, 수정자가 자동으로 업데이트 될 것
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("lyk");    //TODO: 스프링 시큐리티로 인증 기능을 붙일 때 수정할 것
    }
}
