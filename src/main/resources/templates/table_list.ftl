<#include "common/header.ftl">
    <script>
        $(function () {
            var $masterCheckbox = $("#masterCheckbox");
            var num = $("tbody tr").length;
            $masterCheckbox.click(function () {
                if ($masterCheckbox.prop("checked") == true) {
                    // 上面的复选框已被选中
                    $(":checkbox[name='childrenCheckbox']").prop("checked", true);
                } else {
                    // 上面的复选框没被选中
                    $(":checkbox[name='childrenCheckbox']").prop("checked", false);
                }
            });

            var now = $("#now");
            setInterval(function () {
                var time = new Date();   // 程序计时的月从0开始取值后+1
                var m = time.getMonth() + 1;
                var t = time.getFullYear() + "-" + m + "-"
                        + time.getDate() + " " + time.getHours() + ":"
                        + time.getMinutes() + ":" + time.getSeconds();
                now.html("当前时间："+t) ;
            }, 1000);
        });
    </script>
<div class="am-cf admin-main">
    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding am-padding-bottom-0">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表</strong> /
                    <small>Table</small>
                </div>
            </div>
            <hr>
            <div class="am-g">
            </div>
            <div class="am-g">
                <div class="am-u-sm-12">
                    <form class="am-form">
                        <table class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <th class="table-check"><input type="checkbox" name="masterCheckbox" id="masterCheckbox"></th>
                                <th class="table-id">ID</th>
                                <th class="table-title">表名</th>
                                <th class="table-type">注解</th>
                                <th class="table-author am-hide-sm-only">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <#list tableList as table >
                            <tr>
                                <td><input type="checkbox" id="childrenCheckbox" name="childrenCheckbox"></td>
                                <td>${table_index+1}</td>
                                <td><#if (table.tableName)??>${table.tableName}</#if></td>
                                <td><#if (table.comments)??>${table.comments}</#if></td>
                                <td>
                                    <a href="${base}/column/<#if (table.tableName)??>${table.tableName}</#if>?url=<#if (dbConfig.url)??>${dbConfig.url}</#if>&driver=<#if (dbConfig.driver)??>${dbConfig.driver}</#if>&username=<#if (dbConfig.username)??>${dbConfig.username}</#if>&password=<#if (dbConfig.password)??>${dbConfig.password}</#if>&schema=<#if (dbConfig.schema)??>${dbConfig.schema}</#if>">修改</a>
                                </td>
                            </tr>
                            </#list>

                            </tbody>
                        </table>
                        <hr>
                        <p>注：.....</p>
                    </form>
                </div>
            </div>
        </div>
        <#--<footer class="admin-content-footer">-->
            <#--<hr>-->
            <#--<p class="am-padding-horizontal">© helog &nbsp;&nbsp;&nbsp;进入当前页面时间：${.now?string("yyyy-MM-dd HH:mm:ss")}-->
            <#--</p>-->
           <#--<p class="am-padding-horizontal" id="now"></p>-->
        <#--</footer>-->
    </div>
</div>
<#include "common/footer.ftl">
</body>
</html>