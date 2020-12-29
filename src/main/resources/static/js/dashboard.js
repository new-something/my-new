function togglePopup(target, current) {
    target.classList.toggle("is-active");
    if (current) {
        current.classList.remove("is-active");
    }
}

// Mock Interaction - Shortcut List 아이템 유무에 따른 화면 전환.
function shortcutListState(state) {
    let viewEmptyList = document.querySelector('.list-shortcuts-empty');
    let viewShortcutList = document.querySelector('.list-shortcuts');

    if (state === 'empty') {
        viewEmptyList.classList.remove('hidden');
        viewShortcutList.classList.add('hidden');
    } else if (state === 'notEmpty') {
        viewEmptyList.classList.add('hidden');
        viewShortcutList.classList.remove('hidden');
    }
}

// Mock Interaction - 앱 디테일 페이지에서 Connect / Disconnect 버튼을 눌렀을 때의 상태 변경
function changeAppConnectivity(state) {
    if (state === 'connect') {
        let targets = ['appDetailPopup', 'asanaInAppList', 'asanaInConnectedAppList'];
        targets.forEach(target => target.classList.toggle('is-connected'));
    } else if (state === 'disconnect') {
        let disconnect = confirm('If you disconnect Asana, all the Asana shortcuts will be deleted. Are you sure to continue to disconnect?');
        if (disconnect) {
            let targets = ['appDetailPopup', 'asanaInAppList', 'asanaInConnectedAppList'];
            targets.forEach(target => target.classList.toggle('is-connected'));
            appShortcutItem.classList.add('hidden');
            // 숏컷 리스트의 상태를 확인하자.
            let shortcutList = document.querySelectorAll('.list-shortcuts .hidden');

            if (shortcutList.length === 3) {
                shortcutListState('empty');
            } else {
                shortcutListState('notEmpty');
            }
        }
    }
}

// Mock Interaction - 앱 디테일 페이지에서 Add To Shortcut 버튼을 눌렀을 때
function whenClickAddShortcutButton(type) {
    // Empty 상태이던 목록에 새로운 아이템이 추가된다. 해당 아이템은 is-editing 상태
    // 원래는 Add to shortcut 버튼을 누를 때 마다 새로운 숏컷 아이템이 추가되어야 하지만 프로토타입에 포함되지 않았음.
    shortcutListState('notEmpty');
    makeShortcutItemEditable(type);

    if (type === 'app') {
        let inputAppShortcutKeyword = appShortcutItem.querySelector('.shortcut-keyword');
        let saveButton = appShortcutItem.querySelector('.btn-save');

        inputAppShortcutKeyword.textContent = '';
        saveButton.classList.add('is-disabled');

        // 앱 디테일 페이지(팝업)에서 벗어난다.
        togglePopup('appDetailPopup');
    }
}

// Mock Interaction - 숏컷 아이템 내용 수정 가능한 상태로 변경
function makeShortcutItemEditable(type) {
    if (type === 'app') {
        appShortcutItem.classList.add('is-editing');
        appShortcutItem.classList.remove('hidden');

        let inputAppShortcutKeyword = appShortcutItem.querySelector('.shortcut-keyword');

        // 수정 가능 상태에서는 키워드 입력창의 contenteditable 값이 true.
        inputAppShortcutKeyword.setAttribute('contenteditable', true);
        inputAppShortcutKeyword.focus();

    }
    // 수정 가능 상태의 아이템이 활성화 되어 있을 경우 다른 버튼들은 숨겨주자.
    addNewAppButton.classList.add('hidden');

    // connected Apps에 보여지는 샘플 앱의 onclick 이벤트 무효화
    githubInConnectedAppList.style.pointerEvents = 'none';
    asanaInConnectedAppList.style.pointerEvents = 'none';
}

// Mock Interaction - 숏컷 아이템 내용 수정 불가한 상태로 변경
function makeShortcutItemDiseditable(type) {
    if (type === 'app') {
        appShortcutItem.classList.remove('is-editing');

        let inputAppShortcutKeyword = appShortcutItem.querySelector('.shortcut-keyword');
        inputAppShortcutKeyword.setAttribute('contenteditable', false);
    }

}


// Mock Interaction - 숏컷 아이템을 저장할 때
function whenClickSaveItemButton(type) {
    if (type === 'app') {
        let inputAppShortcutKeyword = appShortcutItem.querySelector('.shortcut-keyword');
        let value = inputAppShortcutKeyword.textContent;
        let length = value.length;
        let computedValue = value.replace(/\s+/g, '-').replace(/[^A-Za-z0-9_-]/g, '').toLowerCase();
        let printedShortcutURL = appShortcutItem.querySelector('.shortcut-url-with-keyword');

        if (length === 0) {
            return false;
        } else {
            // 입력된 값을 체크하여 저장 가능한 형태로 변경한다.
            inputAppShortcutKeyword.textContent = printedShortcutURL.textContent = computedValue;
            hrefShortcutURL.setAttribute('href', 'https://my.new/' + computedValue);

            if (computedValue.length > 0) {
                makeShortcutItemDiseditable('app');
            } else {
                inputAppShortcutKeyword.focus();

                let saveButton = appShortcutItem.querySelector('.btn-save');
                saveButton.classList.add('is-disabled');

                return false;
            }
        }
    }
}

function whenClickDeleteItemButton(type) {
    if (type === 'app') {
        appShortcutItem.classList.add('hidden');
    } else if (type === 'url') {

    } else {
        return;
    }
    makeShortcutItemDiseditable(type);
}

// Mock Interaction - 숏컷 아이템을 수정할 때
function whenClickEditItemButton(type) {
    makeShortcutItemEditable(type)
}