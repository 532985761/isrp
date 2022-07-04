package com.grouptwo.isrp.pojo;

import com.grouptwo.isrp.entity.IsrpOrderProcess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: package-lock.json
 * @description: isrporderprocess修改类
 * @author: Wilburn
 * @create: 2022-07-04 08:19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsrpOrderEditPojo {
    private List<IsrpOrderProcess> editOrderProcess;
    private List<IsrpOrderProcess> delOrderProcess;
    private List<IsrpOrderProcessPojo> addOrderProcess;
}
