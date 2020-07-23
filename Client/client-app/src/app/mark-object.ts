import { Mark } from './mark';

export class MarkObject implements Mark {
    id: number;
    description: string;
    mark: number;
    weight: number;
    course: {
        id: number;
        code: string;
        semester: number;
        description: string;
        credits: number;
    };
    category: {
        id: number;
        description: string;
    }

    constructor(id: number, description: string, mark: number, weight:number) {
        this.id = id;
        this.description = description;
        this.mark = mark;
        this.weight = weight;
    }
}