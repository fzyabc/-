package com.fzy.canal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanalData {
    private List<String> data;
    private boolean isDdL;
    private List<String> old;
    private String type;
}
