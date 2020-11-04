package cn.niudehua.efficientprogramming.stream.cases;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限管理功能模块，查询某用户所有校色下所包含的权限名称
 *
 * @author deng
 * @datetime 2020/11/4 11:49 下午
 */
public class CaseThree {
    // 角色
    @Data
    @AllArgsConstructor
    class Role {
        /**
         * 权限列表
         */
        private List<Permission> permissionList;

    }

    // 权限
    @Data
    @AllArgsConstructor
    class Permission {
        /**
         * 权限名称
         */
        private String name;
    }

    /**
     * 用户角色列表
     */
    List<Role> roleList;

    @Before
    public void init() {
        roleList = Lists.newArrayList();
        Role adminRole = new Role(Lists.newArrayList(
                new Permission("删除"),
                new Permission("查看"),
                new Permission("导出")
        ));
        Role userRole = new Role(Lists.newArrayList(
                new Permission("新建"),
                new Permission("修改"),
                new Permission("删除"),
                new Permission("查看")
        ));
        roleList.add(userRole);
        roleList.add(adminRole);
    }

    @Test
    public void findPermission() {
        List<Permission> collect = roleList.stream()
                .flatMap(role -> role.getPermissionList().stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(JSON.toJSONString(collect, true));
    }
}
