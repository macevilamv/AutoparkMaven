<%@ page import="java.util.List" %>
<%@ page import="by.incubator.autopark.dto.VehicleDto" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.util.Timer" %>
<%@ page import="java.util.TimerTask" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="by.incubator.autopark.service.ScheduledService" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Результаты плановой диагностики</title>
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
        <th>Бак</th>
        <th>Была исправна</th>
        <th>Починена</th>
      </tr>
      <%
        List<Long> brokenCars = (List<Long>) request.getAttribute("brokenCars");
        List<VehicleDto> carsDtoList = (List<VehicleDto>) request.getAttribute("cars");
        String broken = "";
      %>
      <%for (VehicleDto vehiclesDto : carsDtoList) {
        if (brokenCars.contains(vehiclesDto.getId())) {
          broken = "нет";
        } else {
          broken = "да";
        }
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
        <td><%=broken%></td>
        <td>да</td>
      </tr>
      <%}%>
    </table>
    <p> Период: 5 минут </p>
    <p> Последняя проверка проведена <strong>
    <% ScheduledService service = new ScheduledService();%>
      <%=service.getLastCheckTime()%>
    <br />
    <hr />
    <br />
  </div>
</div>
</body>
</html>