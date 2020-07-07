import { Mark } from './mark';

export const MARKS: Mark[] = [
  {
    id: 22,
    description: "Devoir 3: Les requetes SQL",
    mark: 100.0,
    weight: 10.0,
    course: {
        id: 1,
        code: "IFT1005",
        semester: 1,
        description: "Design et developpement Web",
        credits: 3.0
    },
    category: {
        id: 1,
        description: "work"
    }
  },
  {
    id: 21,
    description: "Devoir 2: L'interface pour les Expos",
    mark: 100.0,
    weight: 10.0,
    course: {
        id: 1,
        code: "IFT1005",
        semester: 1,
        description: "Design et developpement Web",
        credits: 3.0
    },
    category: {
        id: 1,
        description: "work"
    }
  }
];