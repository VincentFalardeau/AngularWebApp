import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  messages: string[] = [];

  add(message: string) {
    this.messages.push(message);

    //Removes the new message 10 seconds after adding it.
    setTimeout(function (messages:string[]) {
      messages.pop();
    }, 5000, this.messages);
  }
}