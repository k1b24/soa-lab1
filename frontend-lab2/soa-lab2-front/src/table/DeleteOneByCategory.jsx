import {useState} from "react";
import {fetchDeleteOneByCategory} from "../utils/space-marines/api";

const DeleteOneByCategory = ({updateContent, alertWithMessage}) => {

    const [category, setCategory] = useState("")

    const onButtonClick = () => {
        fetchDeleteOneByCategory(category, alertWithMessage)
            .then(() => updateContent())
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Delete one by category</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
            <li>
                <div>
                    <h1>Delete one by transport</h1>
                    <div>
                        <label htmlFor="categoryArea">category</label>
                        <textarea id={"categoryArea"} value={category} onChange={e => setCategory(e.target.value)}
                                  placeholder={"transport"}/>
                    </div>
                    <button className="btn btn-outline btn-warning" onClick={onButtonClick}>Delete one by category
                    </button>
                </div>
            </li>
        </ul>
    </details>
}

export default DeleteOneByCategory