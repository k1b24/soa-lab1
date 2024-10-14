import {fetchGetCountOfHealthyMarines} from "../utils/space-marines/api";

const GetCountOfHealthyMarines = ({alertWithMessage}) => {


    const onButtonClick = () => {
        fetchGetCountOfHealthyMarines(alertWithMessage)
    }

    return <details className="dropdown">
        <summary className="m-1 btn">Get count of healthy marines</summary>
        <ul className="p-2 shadow menu dropdown-content z-[1] bg-base-100 rounded-box w-52">
            <li>
                <div>
                    <button className={"btn btn-outline btn-success"} onClick={onButtonClick}>Get count of healthy marines</button>
                </div>
            </li>
        </ul>
    </details>
}

export default GetCountOfHealthyMarines