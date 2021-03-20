class Extract {
    // metodo de extrair valores de um array de objetos json
    static extract(obj, object, values){
        const keys = Object.keys(object)
        if (keys.length > 0){
            for (let key of keys){
                if (Object.keys(object[key]).length === 0){
                    values.push(obj[key])
                }
                this.extract(obj[key], object[key], values)
            }
        }
    }

    static extractToKey(objects){
        const update = []

        if (objects.length % 2 === 0){
            for (let val of objects){
                const now = {
                    text: this.replaceVal(val)
                }
                update.push(now)
            }
        } else {
            for (let val of objects){
                const now = {
                    text: this.replaceVal(val)
                }
                update.push(now)
            }
            update.push({text: ''});
        }
        return update;
    }

    static replaceVal(val){
        return val.replace(/[•]/g, '-');
    }

    static extractOfArrayToKey(objects, object){
        const extract = this.extractOfArray(objects, object)
        const newArray = []
        for (let obj of extract){
            const update = []
            if (obj.length % 2 === 0){
                for (let val of obj){
                    const now = {
                        text: this.replaceVal(val)
                    }
                    update.push(now)
                }
            } else {
                for (let val of obj){
                    const now = {
                        text: this.replaceVal(val)
                    }
                    update.push(now)
                }
                update.push({text: ''})
            }
            newArray.push(update)
        }
        return newArray;
    }

    static extractOfArray(objects, object){
        const extract = []
        for (let obj of objects){
            const values = [];
            this.extract(obj, object, values);
            extract.push(values)
        }
        return extract;
    }
}
