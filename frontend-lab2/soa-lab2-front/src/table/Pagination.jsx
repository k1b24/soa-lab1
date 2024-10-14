const Pagination = ({currentPage, setCurrentPage, pagesNumber}) => {

    const onNextBackButtonClick = (isNext) => {
        if (isNext && currentPage < (pagesNumber - 1)) {
            setCurrentPage(currentPage + 1)
        }
        if (!isNext && currentPage > 0) {
            setCurrentPage(currentPage - 1)
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