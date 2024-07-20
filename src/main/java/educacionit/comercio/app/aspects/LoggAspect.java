package educacionit.comercio.app.aspects;

import educacionit.comercio.app.entities.Product;
import educacionit.comercio.app.entities.RecordException;
import educacionit.comercio.app.entities.RecordInteraction;
import educacionit.comercio.app.services.RecordExceptionService;
import educacionit.comercio.app.services.impl.InteractionServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Log4j2
@Component
@Aspect
public class LoggAspect {

    @Autowired
    private InteractionServiceImpl interactionService;

    @Autowired
    private RecordExceptionService recordExceptionService;


//###########################  nombre de poincuts

    @Pointcut("execution(* educacionit.comercio.app.controllers.*.*(..))")
    public void allControllerMethod(){

    }


//##############################    Antes de ejecutar el metodo

//La palabra 'execution' siemre va y pasamos por parametros un patron de clases y paquetes el cual es la firma del metodo.
//Se indica primero el tipo de dato que retorna, el '*' es un comodin
//Luego se indica la los paquetes y clases, todas separadas por '.' un punto y pueden ser reemplazadps por el comodin
//tambien se indica el nombre del metodo el cual puede ser reemplazado por un comodin
//y al final se indica los parametros que puede recivir el metodo a escanear
// los '..' dos puntos indica que el metodo puede tener 0 o mas parametros.
    @Before("allControllerMethod()")
    public void loggController(JoinPoint joinPoint){
        String controllerName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        LocalDateTime date = LocalDateTime.now();
        String dateStr = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Object[] args = joinPoint.getArgs();

        RecordInteraction interaction = new RecordInteraction();
        interaction.setController(controllerName);
        interaction.setMethod(methodName);
        interaction.setParameters(Arrays.toString(args));
        interaction.setDate(date);
        interactionService.save(interaction);
        log.info("Argumentos del método: " + Arrays.toString(args));
        log.info("Se accedio al Controlador: " + controllerName + ", Metodo: " + methodName + ", el la fecha: " + dateStr);
    }

    //Espesifico a un controlador en este caso el HomeController y todos sus metodos
    @Before("execution(* educacionit.comercio.app.controllers.HomeController.*(..))")
    public void loggControllerHome(JoinPoint joinPoint){
        String controllerName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("Log para SOLO para HomeController y todos sus metodos");
        log.info("Se accedio al Controlador: " + controllerName + ", Metodo: " + methodName + ", el la fecha: " + date);
    }


    //Espesifico para un solo metodo
    @Before("execution(* educacionit.comercio.app.controllers.HomeController.home2(..))")
    public void loggControllerHome2(JoinPoint joinPoint){
        String controllerName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("Log para SOLO para HomeController y en espesifico el metodo home2");
        log.info("Se accedio al Controlador: " + controllerName + ", Metodo: " + methodName + ", el la fecha: " + date);
    }



//##############################  Despues de ejecutar el metodo
    //Mostramos un log despues de ejecutar cualquier metodo de cualquier clase dentro del paquete Services
    @After("execution(* educacionit.comercio.app.services.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("Despues de ejecutar el metodo: " + joinPoint.getSignature().getName() +
                ", fecha: " + date);
    }


    //Este aspecto se ejecuta despues de que el metdo se haya ejecutado con exito y retornado algo (solo para metodos que retornan cosas)
    @AfterReturning(pointcut = "execution(java.util.List<educacionit.comercio.app.entities.Product> educacionit.comercio.app.services.*.*(..))", returning = "result")
    public void logAfterReturning(Object result) {

        log.info("######## imprimiendo lisa de productos despues de que se ejecuto y retorno el resultado ######");
        ((List< Product>)(result)).forEach(log::info);
        log.info("######## fin de la impresion ######");
    }


    //Esta aspecto se ejecuta luego de que las clases observadas lancen una exception
    @AfterThrowing(pointcut = "execution(* educacionit.comercio.app.services.*.*(..))", throwing = "error")
    public void logAfterThrowing(RuntimeException  error) {
        log.info("Se lanzo la excepcion: " + error + ". Persistiendo en la tabla de registro de errores.");
        //String controllerName = joinPoint.getTarget().getClass().getSimpleName();
        //String methodName = joinPoint.getSignature().getName();
        LocalDateTime date = LocalDateTime.now();

        RecordException recordException = new RecordException();
        recordException.setException(error.toString());
        recordException.setMessage(error.getMessage());
        //recordException.setMethod(methodName);
        //recordException.setController(controllerName);
        recordException.setDate(date);
        recordExceptionService.save(recordException);
        //Aca guardariamos el error ocurrido en la db si tuvieramos una.
    }


//############################## antes y despues del metodo

    //El arround nos permiete capturar operar antes de la ejecucion del metodo, manipular el retorno y operar despues de la ejecucion del metodo
   // @Around("execution(java.util.List<educacionit.comercio.app.entities.Product> educacionit.comercio.app.services.*.*(..))")
    public Object logAndModifyReturn(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Antes del metodo: {}", joinPoint.getSignature().getName());

        // Ejecutar el método y obtener el resultado
        Object result = joinPoint.proceed();

        // Verificar si el resultado es una lista de productos
        if (result instanceof List) {
            List<?> resultList = (List<?>) result;
            for (Object obj : resultList) {
                if (obj instanceof Product) {
                    Product product = (Product) obj;
                    product.setName(product.getName().toUpperCase());
                }
            }
        }

        log.info("Despues de ejecutar el metodo: {}", joinPoint.getSignature().getName());
        log.info("Productos modificados: {}", result);

        return result;
    }
}
