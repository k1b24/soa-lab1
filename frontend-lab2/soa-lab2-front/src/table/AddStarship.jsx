import {useState} from "react";
import {fetchAdd} from "../utils/starships/api";

const AddStarship = ({alertWithMessage}) => {

    const [id, setId] = useState("")
    const [name, setName] = useState("")

    const onCreateButtonClick = () => {
        fetchAdd({
            id: id,
            name: name
        }, alertWithMessage)
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Add starship</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
                <div>
                    <div>
                        <label htmlFor="idArea">id</label>
                        <textarea id={"idArea"} value={id} onChange={e => setId(e.target.value)} placeholder={"id"}/>
                        <label htmlFor="nameArea">name</label>
                        <textarea id={"nameArea"} value={name} onChange={e => setName(e.target.value)} placeholder={"name"}/>
                    </div>
                    <button className={"btn btn-outline btn-success"} onClick={onCreateButtonClick}>Create</button>
                </div>
        </ul>
    </details>
}

export default AddStarship