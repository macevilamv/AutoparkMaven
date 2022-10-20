
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormatSymbols" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="by.incubator.autopark.dto.VehicleDto" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Отчет о прибыли</title>
  <link rel = stylesheet href = "../resources/css/style.css" />
</head>
<body>
<div class = "center-flex-full-vh">
  <div class="vertical-center">
    <a class="ml-20" href="/">На главную</a>
    <br />
    <br />
    <hr />
    <br />
    <table>
      <caption>Машины</caption>
      <tr>
        <th>Тип</th>
        <th>Модель</th>
        <th>Номер</th>
        <th>Масса</th>
        <th>Дата выпуска</th>
        <th>Цвет</th>
        <th>Модель двигателя</th>
        <th>Пробег</th>
        <th>Доход с аренд</th>
        <th>Налог</th>
        <th>Итог</th>
      </tr>
      <%
        List<VehicleDto> carsDtoList = (List<VehicleDto>) request.getAttribute("cars");
      %>
      <%for (VehicleDto vehiclesDto : carsDtoList) {
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
        <td><%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(vehiclesDto.getIncome())%></td>
        <td><%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(vehiclesDto.getTax())%></td>
        <td><%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(vehiclesDto.getProfit())%></td>
      </tr>
      <%}%>
    </table>
    <p> Средний налог за месяц: <strong>
      <%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(request.getAttribute("averageTax"))%>
    </strong> </p>
    <p> Средний доход за месяц: <strong>
      <%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(request.getAttribute("averageIncome"))%>
    </strong> </p>
    <p> Суммарная прибыль автопарка: <strong>
      <%=new DecimalFormat("#0.0",  new DecimalFormatSymbols(Locale.US)).format(request.getAttribute("totalProfit"))%>
    </strong> </p>
    <br />
    <hr />
    <br />
  </div>
</div>
</body>
</html>