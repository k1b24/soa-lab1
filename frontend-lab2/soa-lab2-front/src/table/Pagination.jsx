const Pagination = ({limit, offset, setOffset}) => {

    const onNextBackButtonClick = (isNext) => {
        if (isNext) {
            setOffset(offset + limit)
        }
        if (!isNext && offset > 0) {
            setOffset(offset - limit)
        }
    }

    return <div>
        <div className="join">
            <button className="join-item btn" onClick={() => onNextBackButtonClick(false)}>«</button>
            <button className="join-item btn">Page +-1</button>
            <button className="join-item btn" onClick={() => onNextBackButtonClick(true)}>»</button>
        </div>
    </div>
}

export default Pagination