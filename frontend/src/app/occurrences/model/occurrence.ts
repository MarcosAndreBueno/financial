export interface Occurrence {
    id: number,
    amount: string,
    date: string,
    type: {
        id: number,
        type: string
    },
    category: {
        id: number,
        category: string
    },
    description: string,
}