import React, { useState, useEffect } from 'react';

const AppliList = ({ name, rank, checkedItems, checkedItemHandler }) => {
    const [isChecked, setIsChecked] = useState(null)

    const onCheck = ({ target }) => {
        checkedItemHandler(target.value, target.checked)
        setIsChecked(target.checked)
    }

    useEffect(() => {
        if (checkedItems.includes(name)) {
            setIsChecked(true)
        } else {
            setIsChecked(false)
        }
    }, [checkedItems])

    return (
        <> 
            <div>팀원 : {name} {rank}
            <label>
                <input type='checkbox'
                    checked={isChecked}
                    value={name}
                    onChange={e => onCheck(e)}
                    className='hidden' />
            </label>
            </div>
        </>
    );
};

export default AppliList;