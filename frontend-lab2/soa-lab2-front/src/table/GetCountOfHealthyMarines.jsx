import {fetchGetCountOfHealthyMarines} from "../utils/space-marines/api";
import {useState} from "react";

const GetCountOfHealthyMarines = ({alertWithMessage}) => {

    const [minHealth, setMinHealth] = useState("1")

    const onButtonClick = () => {
        fetchGetCountOfHealthyMarines(minHealth, alertWithMessage)
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Get count of healthy marines</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
                <div>
                    <div>
                        <label htmlFor="healthArea">minHealth</label>
                        <textarea id={"healthArea"} value={minHealth} onChange={e => setMinHealth(e.target.value)} placeholder={"minHealth"}/>
                    </div>
                    <button className={"btn btn-outline btn-success"} onClick={onButtonClick}>Get count of healthy marines</button>
                </div>
        </ul>
    </details>
}

export default GetCountOfHealthyMarines