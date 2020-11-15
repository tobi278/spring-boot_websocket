package it.karger.websocket

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import java.util.*

@Controller
class Controller {

    @Autowired
    private val template: SimpMessagingTemplate? = null

    @MessageMapping("/chat")
    @Throws(Exception::class)
    fun send(message: Message) {
        val time = SimpleDateFormat("HH:mm").format(Date())
        this.template!!.convertAndSend("/topic/messages", OutputMessage(message.from, message.text, time))
    }
}

data class Message(val from: String? = null, val text: String? = null)

data class OutputMessage(val from: String? = null, val text: String? = null, val time: String)
