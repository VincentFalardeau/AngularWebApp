import { Message } from './message';

export class MessageObject implements Message {
    message: string;
    success: boolean;

    constructor(message: string, success: boolean) {
        this.message = message;
        this.success = success;
    }
}