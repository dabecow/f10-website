package team.f10.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import team.f10.utils.QuotesUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Controller
@AllArgsConstructor
public class CustomErrorController implements ErrorController {

    private final QuotesUtil quotesUtil;

    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        log.error("Handled error with status " + status);
        model.addAttribute("message", Objects.nonNull(status) ? status.toString() : "Unexpected error");
        Pair<String, String> quote = quotesUtil.getQuote();
        model.addAttribute("quoteText", quote.getFirst());
        model.addAttribute("quoteAuthor", quote.getSecond());

        return "statuses/error";
    }
}
