package com.example.application.views.helloworld;

import com.example.application.OpenAI;
import com.example.application.model.Message;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Chat")
@Route(value = "gtp-chat")
@RouteAlias(value = "")
public class ChatView extends VerticalLayout {

    private MessageList chat;
    private MessageInput input;

    @Autowired
    private OpenAI openAI;
    @Autowired
    private String userAvatar ;
    @Autowired
    private String aiAvatar;
    @Autowired
    private String systemAvatar;

    public ChatView() {
        chat = new MessageList();
        input = new MessageInput();
        add(chat, input);

        // Full-size center and add padding
        this.setHorizontalComponentAlignment(Alignment.CENTER, chat, input);
        this.setPadding(true); // Leave some white space
        this.setHeightFull(); // We maximize to window
        chat.setSizeFull(); // Chat takes most of the space
        input.setWidthFull(); // Full width only
        chat.setMaxWidth("800px"); // Until to certain size
        input.setMaxWidth("800px"); // Until to certain size

        input.addSubmitListener(this::onSubmit);
    }

    private void onSubmit(MessageInput.SubmitEvent submitEvent) {
        // Append an item (this will be overridden later when reply comes)
        List<MessageListItem> items = new ArrayList<>(chat.getItems());
        MessageListItem inputItem = new MessageListItem(submitEvent.getValue(), Instant.now(), formatName("user"), getAvatar("user"));
        items.add(inputItem);
        chat.setItems(items);

        // Query AI
        openAI.sendAsync(submitEvent.getValue()).whenComplete((messages, t) -> {
            getUI().get().access(() -> {
                chat.setItems(messages.stream().map(this::convertMessage).collect(Collectors.toList()));
            });
        });
    }

    private MessageListItem convertMessage(Message msg) {
        if ("user".equals(msg.getRole())) {
            return new MessageListItem(msg.getContent(), msg.getTime(), msg.getRole(), userAvatar);
        }else if ("assistant".equals(msg.getRole())){
            return new MessageListItem(msg.getContent(), msg.getTime(), msg.getRole(), aiAvatar);
        }else{
            return new MessageListItem(msg.getContent(), msg.getTime(), msg.getRole(), systemAvatar);
        }
    }

    private String getAvatar(String role) {
        if ("assistant".equals(role)) {
            return aiAvatar;
        }
        if ("user".equals(role)) {
            return userAvatar;
        }
        return systemAvatar;
    }

    private String formatName(String role) {
        return role != null && !role.isEmpty()? role.substring(0,1).toUpperCase()+role.substring(1): role;
    }

}
