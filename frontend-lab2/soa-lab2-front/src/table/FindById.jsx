import {useState} from "react";
import {fetchFlatById} from "../utils/flats/api";

const FindById = ({flats, setFlats, updateContent, alertWithMessage}) => {

    const [value, setValue] = useState("")

    const onFindClick = () => {
        fetchFlatById(value, setFlats, alertWithMessage)
    }

    const onResetClick = () => {
        setValue("")
        updateContent()
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Find by id</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
            <li>
                <div>
                    <div>
                            <textarea placeholder={"id"} value={value}
                                      onChange={e => setValue(e.target.value)}></textarea>
                    </div>
                    <button className={"btn btn-outline btn-success"} onClick={onFindClick}>Find</button>
                    <button className="btn btn-outline btn-warning" onClick={onResetClick}>Reset</button>
                </div>
            </li>
        </ul>
    </details>
}

export default FindById