/**
 * Class SimpleBot
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Apr 2, 2016
 */
package bot;
import java.util.*;

public class SimpleBot {
    static final String BOT_NAME = "Bot: ";
    static final String[] COMMON_PHRASES = {
        "Нет ничего ценнее слов, сказанных к месту и ко времени.",
        "Порой молчание может сказать больше, нежели уйма слов.",
        "Прежде чем спросить задумайся - нужен ли тебе ответ?",
        "Вежливая и грамотная речь говорит о величии души.",
        "Многословие есть признак неупорядоченного ума.",
        "Слова могут ранить, но могут и исцелять.",
        "Вначале обдумай и лишь потом высказывай.",
        "Записывая слова, мы удваиваем их силу.",
        "Кто ясно мыслит, тот ясно излагает.",
        "Лучше два слова - но от сердца."};
    static final String[] ELUSIVE_ANSWERS = {
        "Не уверен, что располагаю этой информацией.",
        "Порой лучше что-то не знать, чем знать.",
        "Простите, но это очень личный вопрос.",
        "Боюсь ответ вам может не понравиться.",
        "Вы действительно хотите это знать?",
        "Уверен, вы уже догадались сами."};

    public SimpleBot() {
    }

    public String sayInReturn(String msg) {
        Random random = new Random();
        return BOT_NAME + 
            ((msg.charAt(msg.length() - 1) == '?')?
            ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
            COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)]);
    }
}