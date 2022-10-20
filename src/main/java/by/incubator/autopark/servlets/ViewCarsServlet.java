package by.incubator.autopark.servlets;

import by.incubator.autopark.facade.FacadeService;
import by.incubator.autopark.infrastructure.core.ContextService;
import by.incubator.autopark.infrastructure.core.impl.ApplicationContext;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/viewCars")
public class ViewCarsServlet extends HttpServlet {
    FacadeService dtoService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ContextService service = new ContextService();
        ApplicationContext context = service.getContext();
        dtoService = context.getObject(FacadeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", dtoService.getVehicles());
        getServletContext().getRequestDispatcher("/jsp/viewCarsJSP.jsp").forward(req, resp);
    }

}
