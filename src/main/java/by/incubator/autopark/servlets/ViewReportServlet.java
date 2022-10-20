package by.incubator.autopark.servlets;

import by.incubator.autopark.calculator.Calculator;
import by.incubator.autopark.dto.VehicleDto;
import by.incubator.autopark.facade.FacadeService;
import by.incubator.autopark.infrastructure.core.ContextService;
import by.incubator.autopark.infrastructure.core.impl.ApplicationContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewReport")
public class ViewReportServlet extends HttpServlet {
    FacadeService dtoService;

    @Override
    public void init() throws ServletException {
        super.init();
        ContextService service = new ContextService();
        ApplicationContext context = service.getContext();
        dtoService = context.getObject(FacadeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<VehicleDto> vehiclesDtoList =  dtoService.getVehicles();
        double averageTax = Calculator.calculateAverageTax(vehiclesDtoList);
        double averageIncome = Calculator.calculateAverageIncome(vehiclesDtoList);
        double totalProfit = Calculator.calculateTotalProfit(vehiclesDtoList);

        req.setAttribute("cars", vehiclesDtoList);
        req.setAttribute("averageTax", averageTax);
        req.setAttribute("averageIncome", averageIncome);
        req.setAttribute("totalProfit", totalProfit);

        getServletContext().getRequestDispatcher("/jsp/viewReportJSP.jsp").forward(req, resp);
    }
}
