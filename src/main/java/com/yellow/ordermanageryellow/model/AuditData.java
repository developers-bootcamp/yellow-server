package com.yellow.ordermanageryellow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditData {
    private LocalDate createDate;
    private LocalDate updateDte;
}
