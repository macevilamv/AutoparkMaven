<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.DecimalFormatSymbols" %>
<%@ page import="java.util.Locale" %>
<%@ page import="by.incubator.autopark.dto.VehicleDto" %>
<%@ page import="by.incubator.autopark.dto.RentDto" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Просмотр информации о конкретной машине</title>
    <link rel = stylesheet href = "../resources/css/style.css" />
</head>
<body>
<div class = "center-flex-full-vh">
    <div class="vertical-center">
        <a class="ml-20" href="/">На главную</a>
        <a class="ml-20" href="/viewCars">Назад</a>
        <br />
        <br />
        <hr />
        <br />
        <table>
            <caption>Информация о машине</caption>
            <tr>
                <th>Тип</th>
                <th>Модель</th>
                <th>Номер</th>
                <th>Масса</th>
                <th>Дата выпуска</th>
                <th>Цвет</th>
                <th>Модель двигателя</th>
                <th>Пробег</th>
                <th>Бак</th>
                <th>Расход</th>
                <th>Коэфф налога</th>
                <th>km на полный бак</th>
            </tr>
            <%
                List<VehicleDto> carsDtoList = (List<VehicleDto>) request.getAttribute("cars");
                List<RentDto> rentsDtoList = (List<RentDto>) request.getAttribute("rents");

                VehicleDto vehiclesDto = carsDtoList.get(0);
            %>
            <tr>
                <td><%=vehiclesDto.getTypeName()%></td>
                <td><%=vehiclesDto.getModel()%></td>
                <td><%=vehiclesDto.getRegistrationNumber()%></td>
                <td><%=vehiclesDto.getWeight()%></td>
                <td><%=vehiclesDto.getManufactureYear()%></td>
                <td><%=vehiclesDto.getColor()%></td>
                <td><%=vehiclesDto.getEngineName()%></td>
                <td><%=vehiclesDto.getMileage()%></td>
                <td><%=vehiclesDto.getTankCapacity()%></td>
                <td><%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(vehiclesDto.getPer100Kilometers())%></td>
                <td><%=vehiclesDto.getCoefTaxes()%></td>
                <td><%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(vehiclesDto.getMaxKilometers())%></td>
            </tr>
        </table>
        <p> Налог за месяц: <strong>
            <%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(vehiclesDto.getTax())%>
        </strong> </p>
        <br />
        <hr />
        <br />
        <table>
            <caption>Информация об аренде</caption>
            <tr>
                <th>Дата</th>
                <th>Стоимость</th>
            </tr>
            <%for (RentDto rentsDto : rentsDtoList) {
            %>
            <tr>
                <td><%=rentsDto.getDate()%></td>
                <td><%=rentsDto.getCost()%></td>
            </tr>
            <%}%>
        </table>
        <p> Cумма: <strong>
            <%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(vehiclesDto.getIncome())%>
        </strong></p>
        <p> Доход: <strong>
            <%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(vehiclesDto.getProfit())%>
        </strong></p>
    </div>
</div>
</body>
</html>