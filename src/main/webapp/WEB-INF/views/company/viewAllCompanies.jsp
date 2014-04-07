<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title>All companies</title>

<t:mainPage>
    <script type="text/javascript">
        $(document).ready(function () {
            var grid = $("#list");
            grid.jqGrid({
                url: '/company/all',
                datatype: 'json',
                mtype: 'GET',
                colNames: ['', 'Company name', 'Country', 'Mechanism', 'City', 'Address', 'Phone number', 'Active'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'name', index: 'companyName', align: "left"},
                    {name: 'country.name', index: 'countryName', align: "left"},
                    {name: 'mechanismId', index: 'mechanismId', align: "left"},
                    {name: 'city', index: 'city', align: "left"},
                    {name: 'address', index: 'address', align: "left"},
                    {name: 'phoneNumber', index: 'phoneNumber', align: "left"},
                    {name: 'active', index: 'active',
                        editable: true,
                        align: 'center',
                        edittype: 'checkbox',
                        editoptions: { value: "True:False"},
                        formatter: "checkbox",
                        formatoptions: {disabled: false}}
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