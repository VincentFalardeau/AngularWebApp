import { Message } from './message';

export class MessageObject implements Message {
    message: string;
    success: boolean;//Indicates whether a message is positive or negative.

    constructor(message: string, success: boolean) {
        this.message = message;
        this.success = success;
    }
}