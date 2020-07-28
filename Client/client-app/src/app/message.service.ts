import { Injectable } from '@angular/core';

import { Message } from './message';
import { MessageObject } from './message-object';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  messages: Message[] = [];

  add(message: MessageObject) {
    this.messages.push(message);

    //Removes a message 5 seconds after adding it.
    setTimeout(function (messages:Message[]) {
      messages.pop();
    }, 5000, this.messages);
  }
}