class Line {
    static horizontal(pos){
        const pStart = 3;
        const pEnd = 19;
        return {
            x1: pStart,
            y1: pos,
            x2: pEnd,
            y2: pos,
        }
    }

    static vertical(pWidth, pStart, pEnd){
        return {
            x1: pWidth,
            y1: pStart,
            x2: pWidth,
            y2: pEnd,
        }
    }
}
