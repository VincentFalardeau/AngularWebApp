export interface Mark{
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
}