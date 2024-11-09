import { useState } from "react";
import { useRef } from 'react';

const SortBy = ({setSortBy, sortBy, updateContent}) => {

    const [id, setId] = useState(true)
    const [name, setName] = useState(false)
    const [creationDate, setCreationDate] = useState(false)
    const [health, setHealth] = useState(false)
    const [height, setHeight] = useState(false)
    const [category, setCategory] = useState(false)

    const ref = useRef([]);

    const onSortByClick = () => {
        let newSortBy = []
        if (id) newSortBy.push("ID")
        if (name) newSortBy.push("NAME")
        if (creationDate) newSortBy.push("CREATION_DATE")
        if (health) newSortBy.push("HEALTH")
        if (height) newSortBy.push("HEIGHT")
        if (category) newSortBy.push("CATEGORY")
        setSortBy(newSortBy)
//         updateContent()
    }

    const onResetClick = () => {
        setId(true)
        ref.current[0].checked = true
        for (let i = 1; i < ref.current.length; i++) {
            ref.current[i].checked = false
        }
        setName(false)
        setCreationDate(false)
        setHealth(false)
        setHeight(false)
        setCategory(false)
        onSortByClick()
    }

    return <details className="dropdown">
        <summary className="m-1 btn">SORT BY</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
                <div>
                    <div>
                        <label htmlFor="id">id</label>
                        <input type="checkbox" id={"id"} value={id} onChange={e => setId(e.target.checked)} placeholder={"id"} ref={(element) => { ref.current[0] = element }}/><p/>
                        <label htmlFor="name">name</label>
                        <input type="checkbox" id={"name"} value={name} onChange={e => setName(e.target.checked)} placeholder={"name"} ref={(element) => { ref.current[1] = element }}/><p/>
                        <label htmlFor="creationDate">creationDate</label>
                        <input type="checkbox" id={"creationDate"} value={creationDate} onChange={e => setCreationDate(e.target.checked)} placeholder={"creationDate"} ref={(element) => { ref.current[2] = element }}/><p/>
                        <label htmlFor="health">health</label>
                        <input type="checkbox" id={"health"} value={health} onChange={e => setHealth(e.target.checked)} placeholder={"health"} ref={(element) => { ref.current[3] = element }}/><p/>
                        <label htmlFor="height">height</label>
                        <input type="checkbox" id={"height"} value={height} onChange={e => setHeight(e.target.checked)} placeholder={"height"} ref={(element) => { ref.current[4] = element }}/><p/>
                        <label htmlFor="category">category</label>
                        <input type="checkbox" id={"category"} value={category} onChange={e => setCategory(e.target.checked)} placeholder={"category"} ref={(element) => { ref.current[5] = element }}/><p/>
                    </div>
                    <button className={"btn btn-outline btn-success"} onClick={onSortByClick}>Sort</button>
                    <button className="btn btn-outline btn-warning" onClick={onResetClick}>Reset</button>
                </div>
        </ul>
    </details>
}

export default SortBy