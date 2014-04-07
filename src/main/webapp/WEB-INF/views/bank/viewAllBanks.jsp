<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title>All banks</title>
<style>
    .ui-jqgrid .ui-jqgrid-htable th.ui-th-column
    {
        text-align:left
    }
    .ui-jqgrid .ui-jqgrid-htable th.ui-th-column div.ui-jqgrid-sortable
    {
        margin-left:3px;margin-top:3px
    }
</style>
<t:mainPage>
    <script type="text/javascript">
        $(document).ready(function () {
            var grid = $("#list");
            grid.jqGrid({
                url: '/bank/test',
                datatype: 'json',
                mtype: 'GET',
                colNames: ['', 'Bank name', 'Location', 'Active'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'name', index: 'name', align: "left", sortable: true},
                    {name: 'country.name', index: 'country.name', align: "left", sortable: true},
                    {name: 'active', index: 'active',
                        editable: true,
                        align: 'center',
                        edittype: 'checkbox',
                        editoptions: { value: "True:False"},
                        formatter: "checkbox",
                        formatoptions: {disabled: false},
                        sortable: true}
                ],
                rowNum: 10,
//                rowList: [10, 20, 30],
//                pager: '#pager',
                sortname: 'id',
//                viewrecords: true,
                sortorder: "desc",
                autowidth: true,
                shrinkToFit: true,
                multiselect: true,
                multiselectWidth: 30
            });
//            grid.jqGrid('navGrid', '#pager', {edit: false, add: false, del: false});
            $("#cb_" + grid[0].id).hide();
        });
    </script>

    <div class="container">
        <div class="col-md-offset-1 col-md-10">
            <table class="table" id="list"></table>
            <div id="pager"></div>
        </div>
    </div>
</t:mainPage>