package com.niit.soft.client.api.domain.dto.schoolmate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName StringDto
 * @Description 一段String字符串
 * @Author xiaobinggan
 * @Date 2020/6/22 10:03 上午
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StringDto {
    private String text;
}
