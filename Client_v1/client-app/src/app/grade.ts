export interface Grade{
    average: number;
    gpa: number;
    letter: string;
    course: {
        id: number;
        code: string;
        semester: number;
        description: string;
        credits: number;
    };
}