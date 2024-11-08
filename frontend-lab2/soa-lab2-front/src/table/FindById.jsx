import {useState} from "react";
import {fetchMarineById} from "../utils/space-marines/api";

const FindById = ({marines, setMarines, updateContent, alertWithMessage}) => {

    const [value, setValue] = useState("")

    const onFindClick = () => {
        fetchMarineById(value, setMarines, alertWithMessage)
    }

    const onResetClick = () => {
        setValue("")
        updateContent()
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Find by id</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
                <div>
                    <div>
                            <input type="number" placeholder={"id"} value={value}
                                      onChange={e => setValue(e.target.value)}></input>
                    </div>
                    <button className={"btn btn-outline btn-success"} onClick={onFindClick}>Find</button>
                    <button className="btn btn-outline btn-warning" onClick={onResetClick}>Reset</button>
                </div>
        </ul>
    </details>
}

export default FindById