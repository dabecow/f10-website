package team.f10.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import team.f10.utils.QuotesUtil;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class CustomControllerAdvice {

    private static final String pageMetaName = "Error";
    private final QuotesUtil quotesUtil;

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView generalExceptionHandling(RuntimeException e) {
        log.error("An error occurred. ", e);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("metaName", pageMetaName);
        modelAndView.addObject("message", e.getMessage());
        Pair<String, String> quote = quotesUtil.getQuote();
        modelAndView.addObject("quoteText", quote.getFirst());
        modelAndView.addObject("quoteAuthor", quote.getSecond());
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
